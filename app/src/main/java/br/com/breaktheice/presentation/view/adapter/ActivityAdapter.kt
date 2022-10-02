package br.com.breaktheice.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.breaktheice.R
import br.com.breaktheice.databinding.ItemActivityBinding
import br.com.breaktheice.domain.model.ActivityModel
import com.google.android.material.card.MaterialCardView

/**
 * @author Raphael Santos
 */
class ActivityAdapter(
    private val onActivityClickListener: (ActivityModel) -> Unit,
    private val onActivityFavoriteClickListener: (Int, Boolean) -> Unit
) : RecyclerView.Adapter<ActivityAdapter.ViewHolder>(), IItemContract<ActivityModel> {

    private val activityModelDataSet: ArrayList<ActivityModel?> = arrayListOf()

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

    override fun replaceList(dataSet: List<ActivityModel>) {
        activityModelDataSet.apply {
            clear()
            addAll(dataSet)
            notifyDataSetChanged()
        }
    }

    private fun ViewHolder.onItemClick(activityModel: ActivityModel) {
        val activityCard: MaterialCardView? = itemView.findViewById(R.id.activity_layout)
        activityCard?.setOnClickListener {
            onActivityClickListener.invoke(activityModel)
        }

        val favoriteImage: AppCompatImageView? = itemView.findViewById(R.id.star_image_view)
        favoriteImage?.setOnClickListener {
            onActivityFavoriteClickListener.invoke(activityModel.id, !activityModel.favorite)
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
