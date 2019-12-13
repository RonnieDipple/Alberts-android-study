package com.stepasha.sprintpokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import com.stepasha.sprintpokedex.MainActivity.Companion.pokeList
import com.stepasha.sprintpokedex.adapter.PokemonListAdapter
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val pokemonName = intent.getStringExtra(PokemonListAdapter.POKEMONNAME)
        val pokemonTypes = intent.getStringExtra(PokemonListAdapter.POKEMONTYPE)
        val pokemonPic = intent.getStringExtra(PokemonListAdapter.POKEMONPIC)
        val pokemonID = intent.getStringExtra(PokemonListAdapter.POKEMONID)
        val pokemonAbilities = intent.getStringExtra(PokemonListAdapter.POKEMONABILITY)

        Picasso.get()
            .load(pokemonPic)
            .resize(200, 200)
            .centerCrop()
            .into(detailPokemonPic)
        detailPokemonId.text = pokemonID
        detailPokemonName.text = pokemonName
        detailPokemonAbility.text = pokemonAbilities
        detailPokemonType.text = pokemonTypes

        var pokeList = pokeList
        delete.setOnClickListener {
            for (i in 0 until pokeList.size -1){
                if (pokeList[i].id == pokemonID.toInt()){
                    pokeList.remove(pokeList[i])
                }
                pokeList = pokeList
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        cancel.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }






    }
}
