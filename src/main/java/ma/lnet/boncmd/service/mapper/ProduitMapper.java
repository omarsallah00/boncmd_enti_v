package ma.lnet.boncmd.service.mapper;

import ma.lnet.boncmd.domain.*;
import ma.lnet.boncmd.service.dto.ProduitDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Produit and its DTO ProduitDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProduitMapper {

    @Mapping(source = "categorie.id", target = "categorieId")
    @Mapping(source = "categorie.referenceCategorie", target = "categorieReferenceCategorie")
    ProduitDTO produitToProduitDTO(Produit produit);

    List<ProduitDTO> produitsToProduitDTOs(List<Produit> produits);

    @Mapping(source = "categorieId", target = "categorie")
    Produit produitDTOToProduit(ProduitDTO produitDTO);

    List<Produit> produitDTOsToProduits(List<ProduitDTO> produitDTOs);

    default Categorie categorieFromId(Long id) {
        if (id == null) {
            return null;
        }
        Categorie categorie = new Categorie();
        categorie.setId(id);
        return categorie;
    }
}
