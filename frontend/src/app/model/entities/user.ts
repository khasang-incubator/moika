import {Person} from "./person";
import {BaseMoikaEntity} from "./base-moika-entity";

export class User extends BaseMoikaEntity {
   idUser: number;
   login: string;
   password: string;
   timeCreate: Date;
   timeEdit: Date;
   idPerson: number;
   person: Person;


}
