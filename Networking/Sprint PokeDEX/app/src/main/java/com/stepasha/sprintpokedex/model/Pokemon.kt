package com.stepasha.sprintpokedex.model



data class Sprites (
    val front_default: String
)
data class Ability(val name: String)
data class AbilityList(val ability: Ability)
data class Type(val name: String)
data class TypeList(val type: Type)

data class Pokemon (
    val name: String,
    val sprites: Sprites,
    val id: Int,
    val abilities: List<AbilityList>,
    val types: List<TypeList>
)

