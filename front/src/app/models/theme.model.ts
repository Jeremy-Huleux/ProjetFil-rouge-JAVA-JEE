import { Categorie } from "./categorie.model";

export class Theme{
    id!: number;
    nom!: string;
    description!: string;
    categorie!: Categorie;
}
