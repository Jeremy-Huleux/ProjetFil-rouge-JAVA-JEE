import Formation from "./formation.model";
import { Lieu } from "./lieu.model";
import { User } from "./user.model";

export class Bootcamp{
    id!: number;
    dateDebut!: string;
    dateFin!: string;
    statut!: string;
    lieu!: Lieu;
    formation!: Formation[];
    userCount!: number;
}
