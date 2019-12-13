package com.stepasha.sprintpokedex.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.stepasha.sprintpokedex.DetailActivity
import com.stepasha.sprintpokedex.MainActivity
import com.stepasha.sprintpokedex.R
import com.stepasha.sprintpokedex.model.Pokemon
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_view.view.*

class PokemonListAdapter(var items: List<Pokemon>) : RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {
    companion object {
        const val POKEMONNAME = "POKEMONNAME"
        const val POKEMONID = "POKEMONID"
        const val POKEMONTYPE = "POKEMONTYPE"
        const val POKEMONPIC = "POKEMONPICK"
        const val POKEMONABILITY = "POKEMONABILITY"
    }
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var pokemonButton = view.detailButton as Button
        var cardView = view.pokemonCardview
        var pokeSprite = view.pokemonImage as ImageView
        var pokeName = view.pokemonName as TextView
        var pokeId = view.pokemonId as TextView
        var favSwitch = view.pokemonSwitch as Switch
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val viewGroup = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(viewGroup)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = items[position]
        val context = holder.cardView.context
        holder.pokeId.text = items[position].id.toString()
        holder.pokeName.text = items[position].name
        //for image
        Picasso.get().load(items[position].sprites.front_default)
            .resize(100,100)
            .centerCrop()
            .into(holder.pokeSprite)
        //for switch
        holder.favSwitch.setOnClickListener{
            if(!MainActivity.favoritesList.contains(pokemon)&& holder.favSwitch.isChecked){
                MainActivity.favoritesList.add(pokemon)
            }else if(MainActivity.favoritesList.contains(pokemon) && !holder.favSwitch.isChecked){
                MainActivity.favoritesList.remove(pokemon)
            }
            holder.pokemonButton.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(POKEMONABILITY, pokemon.abilities.toString())
                intent.putExtra(POKEMONID, pokemon.id.toString())
                intent.putExtra(POKEMONNAME, pokemon.name)
                intent.putExtra(POKEMONPIC, pokemon.sprites.front_default)
                intent.putExtra(POKEMONTYPE, pokemon.types.toString())
                context.startActivity(intent)
            }

        }


    }
    fun updateRecyclerView(recycleList: List<Pokemon>){
        items = recycleList
        notifyDataSetChanged()
    }


}