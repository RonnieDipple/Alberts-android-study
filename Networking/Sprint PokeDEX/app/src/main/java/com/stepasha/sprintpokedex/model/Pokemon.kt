package com.stepasha.sprintpokedex.model

import java.io.Serializable

data class Sprites (
    val front_default: String
)
data class Ability(val name: String)
data class AblityList(val ability: Ability)
data class Type(val name: String)
data class TypeList(val type: Type)

data class Pokemon (
    val name: String,
    val sprites: Sprites,
    val id: Int,
    val abilities: List<AblityList>,
    val types: List<TypeList>
)



data class SerializedPokemon(
    var name: String,
    var sprites: String,
    var id: Int,
    var ability: MutableList<String>,
    var type: MutableList<String>
): Serializable