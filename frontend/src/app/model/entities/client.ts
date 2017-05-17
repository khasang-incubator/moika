
import {Person} from "./person";
import {BaseMoikaEntity} from "./base-moika-entity";
import {SomeStatus} from "./some-status";

export class Client extends BaseMoikaEntity {
   idClient: number;
   idPerson: number;
   person: Person;
   idStatus: number;
   status: SomeStatus;
   dateReg: Date;
   dateLastWash: Date;

}
