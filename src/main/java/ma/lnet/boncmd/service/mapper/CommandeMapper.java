package ma.lnet.boncmd.service.mapper;

import ma.lnet.boncmd.domain.*;
import ma.lnet.boncmd.service.dto.CommandeDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Commande and its DTO CommandeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommandeMapper {

    @Mapping(source = "produit.id", target = "produitId")
    @Mapping(source = "produit.referenceProduit", target = "produitReferenceProduit")
    CommandeDTO commandeToCommandeDTO(Commande commande);

    List<CommandeDTO> commandesToCommandeDTOs(List<Commande> commandes);

    @Mapping(source = "produitId", target = "produit")
    Commande commandeDTOToCommande(CommandeDTO commandeDTO);

    List<Commande> commandeDTOsToCommandes(List<CommandeDTO> commandeDTOs);

    default Produit produitFromId(Long id) {
        if (id == null) {
            return null;
        }
        Produit produit = new Produit();
        produit.setId(id);
        return produit;
    }
}
