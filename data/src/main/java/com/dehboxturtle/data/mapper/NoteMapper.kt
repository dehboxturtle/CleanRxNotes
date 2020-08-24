package com.dehboxturtle.data.mapper

import com.dehboxturtle.data.net.dto.NoteDTO
import com.dehboxturtle.data.persistence.entity.NoteEntity
import com.dehboxturtle.domain.model.Note
import javax.inject.Inject

/**
 * Mapper class used to transform [NoteDTO] or [NoteEntity] (in the data layer) to [Note]
 * in the domain layer and vice versa.
 */
class NoteMapper
@Inject constructor() {

    //region DTO to MODEL
    /**
     * Transform a [NoteDTO] into an [Note].
     * @param dto  Object to be transformed.
     * @param userName Foreign key
     * @return [Note] if valid [NoteDTO]
     */
    fun transform(dto: NoteDTO, userName: String): Note =
        Note(dto.id, dto.name, dto.description, dto.url, false, userName)

    /**
     * Transform a Collection of [NoteDTO] into a List of [Note].
     * @param dtoCollection Object Collection to be transformed.
     * @param userName          Foreign key
     * @return list of [Note]
     */
    fun transform(dtoCollection: Collection<NoteDTO>, userName: String): List<Note> =
        dtoCollection.map { transform(it, userName) }
    //endregion

    //region ENTITY to MODEL
    /**
     * Transform a [NoteEntity] into an [Note].
     * @param entity Object to be transformed.
     * @return [Note] if valid [NoteEntity] otherwise null.
     */
    fun transform(entity: NoteEntity): Note =
        Note(
            entity.id,
            entity.name,
            entity.description,
            entity.url,
            entity.isFavorite,
            entity.userName
        )

    /**
     * Transform a Collection of [NoteEntity] into a List of [Note].
     * @param entityCollection Object Collection to be transformed.
     * @return list of [Note]
     */
    fun transform(entityCollection: Collection<NoteEntity>): List<Note> =
        entityCollection.map { transform(it) }
    //endregion

    //region MODEL to ENTITY
    /**
     * Transform a [Note] into an [NoteEntity].
     * @param model Object to be transformed.
     * @return [Note] if valid [NoteEntity] otherwise null.
     */
    fun transformToEntity(model: Note): NoteEntity =
        NoteEntity(
            model.id,
            model.name,
            model.description,
            model.url,
            model.isFavorite,
            model.userName
        )

    /**
     * Transform a Collection of [Note] into a List of [NoteEntity].
     * @param modelCollection Object Collection to be transformed.
     * @return list of [NoteEntity]
     */
    fun transformToEntity(modelCollection: Collection<Note>): List<NoteEntity> =
        modelCollection.map { transformToEntity(it) }
    //endregion

}