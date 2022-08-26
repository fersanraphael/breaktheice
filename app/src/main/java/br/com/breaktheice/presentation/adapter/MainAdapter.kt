package br.com.breaktheice.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import br.com.breaktheice.R
import br.com.breaktheice.databinding.ItemActivityBinding
import br.com.breaktheice.domain.entity.ActivityModel

/**
 * @author Raphael Santos
 */
class MainAdapter(
    val onItemClickListener: (ActivityModel) -> Unit
) : RecyclerView.Adapter<MainAdapter.ViewHolder>(), IItemContract<ActivityModel> {

    private var activityModelDataSet: ArrayList<ActivityModel?> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemActivityBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val activityModel: ActivityModel? = activityModelDataSet[position]
        if (activityModel != null) {
            holder.bind(activityModel)
            holder.onItemClick(activityModel)
        }
    }

    override fun getItemCount(): Int {
        return activityModelDataSet.size
    }

    override fun replaceList(dataSet: MutableList<ActivityModel>) {
        activityModelDataSet.apply {
            clear()
            addAll(dataSet)
            notifyItemRangeChanged(0, dataSet.size)
        }
    }

    private fun ViewHolder.onItemClick(activityModel: ActivityModel) {
        itemView.findViewById<RelativeLayout>(R.id.activity_layout).setOnClickListener {
            onItemClickListener(activityModel)
        }
    }

    inner class ViewHolder(
        private val binding: ItemActivityBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(activityModel: ActivityModel) {
            binding.activityModel = activityModel
            binding.executePendingBindings()
        }
    }
}
