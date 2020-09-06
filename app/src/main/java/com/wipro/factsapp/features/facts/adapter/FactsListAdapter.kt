package com.wipro.factsapp.features.facts.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.wipro.factsapp.R
import com.wipro.factsapp.databinding.LayoutFactsItemBinding
import com.wipro.factsapp.features.facts.model.Facts

class FactsListAdapter(
    private val mContext: Context,
    var factsList: ArrayList<Facts>,
    var onFactsSelected: (Facts, Int) -> Unit
) :
    RecyclerView.Adapter<FactsListAdapter.FactsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FactsViewHolder {

        val mBinding = DataBindingUtil.inflate<LayoutFactsItemBinding>(
            LayoutInflater.from(mContext)
            , R.layout.layout_facts_item, parent, false
        )
        return FactsViewHolder(mBinding.root)
    }

    override fun getItemCount(): Int {
        return factsList.size
    }

    override fun onBindViewHolder(holder: FactsViewHolder, position: Int) {

        if (!factsList.isNullOrEmpty()) {
            val facts: Facts = factsList[position]

            holder.binding?.tvFactTitle?.text = facts.title ?: "Data not available"
            holder.binding?.tvFactsDescription?.text = facts.description ?: "Data not available"

            holder.binding?.imgFactIcon?.let {
                Glide.with(mContext).asBitmap().load(facts.imageHref)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .apply(RequestOptions().placeholder(R.drawable.ic_action_image))
                    .apply(RequestOptions().centerCrop())
                    .dontAnimate()
                    .into(it)
            }

            holder.binding?.rlFactsDetails?.setOnClickListener { onFactsSelected(facts, position) }
        }
    }

    inner class FactsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val binding: LayoutFactsItemBinding? = DataBindingUtil.bind(itemView)
    }
}