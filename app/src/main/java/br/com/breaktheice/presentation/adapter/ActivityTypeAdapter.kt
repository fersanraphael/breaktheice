package br.com.breaktheice.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.breaktheice.R
import br.com.breaktheice.databinding.ItemActivityTypeBinding
import com.google.android.material.card.MaterialCardView

/**
 * @author Raphael Santos
 */
class ActivityTypeAdapter(
    private val onActivityClickListener: (String) -> Unit
) : RecyclerView.Adapter<ActivityTypeAdapter.ViewHolder>(), IItemContract<String> {

    private val activityTypeDataSet: ArrayList<String?> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemActivityTypeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val activityType: String? = activityTypeDataSet[position]
        if (activityType != null) {
            holder.bind(activityType)
            holder.onItemClick(activityType)
        }
    }

    override fun getItemCount(): Int {
        return activityTypeDataSet.size
    }

    override fun replaceList(dataSet: List<String>) {
        activityTypeDataSet.apply {
            clear()
            addAll(dataSet)
            notifyItemRangeChanged(0, dataSet.size)
        }
    }

    private fun ViewHolder.onItemClick(activityType: String) {
        itemView.findViewById<MaterialCardView>(R.id.activity_type_layout).setOnClickListener {
            onActivityClickListener.invoke(activityType)
        }
    }

    inner class ViewHolder(
        private val binding: ItemActivityTypeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(activityType: String) {
            binding.activityType = activityType
            binding.executePendingBindings()
        }
    }
}
