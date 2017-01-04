package ma.lnet.boncmd.service.mapper;

import ma.lnet.boncmd.domain.*;
import ma.lnet.boncmd.service.dto.PanierDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Panier and its DTO PanierDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PanierMapper {

    @Mapping(source = "commande.id", target = "commandeId")
    @Mapping(source = "commande.referenceCommande", target = "commandeReferenceCommande")
    PanierDTO panierToPanierDTO(Panier panier);

    List<PanierDTO> paniersToPanierDTOs(List<Panier> paniers);

    @Mapping(source = "commandeId", target = "commande")
    Panier panierDTOToPanier(PanierDTO panierDTO);

    List<Panier> panierDTOsToPaniers(List<PanierDTO> panierDTOs);

    default Commande commandeFromId(Long id) {
        if (id == null) {
            return null;
        }
        Commande commande = new Commande();
        commande.setId(id);
        return commande;
    }
}
