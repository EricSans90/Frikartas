package com.example.frikartas.domain.models

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "one_piece_collections")
data class OnePieceCollection (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0,

    @Column(name = "name")
    val name: String,

    @Column(name = "publication_year")
    val publicationYear: Int,

    @OneToMany(mappedBy = "collection", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var cards: List<OnePieceCard>
)