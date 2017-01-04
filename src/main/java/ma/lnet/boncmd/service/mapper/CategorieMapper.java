package ma.lnet.boncmd.service.mapper;

import ma.lnet.boncmd.domain.*;
import ma.lnet.boncmd.service.dto.CategorieDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Categorie and its DTO CategorieDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CategorieMapper {

    CategorieDTO categorieToCategorieDTO(Categorie categorie);

    List<CategorieDTO> categoriesToCategorieDTOs(List<Categorie> categories);

    Categorie categorieDTOToCategorie(CategorieDTO categorieDTO);

    List<Categorie> categorieDTOsToCategories(List<CategorieDTO> categorieDTOs);
}
