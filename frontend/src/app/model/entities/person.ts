import {BaseMoikaEntity} from "./base-moika-entity";
import {Phone} from "./phone";

export class Person extends BaseMoikaEntity {
  idPerson: number;
  firstName: string;
  middleName: string = '';
  lastName: string;
  birthDate: Date;
  phones: Phone[];
  email: string = '';
  get fullname(): string {return this.firstName+' '+this.middleName +' '+this.lastName};

}
