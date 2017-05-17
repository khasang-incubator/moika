import {City} from "./city";
import {BaseMoikaEntity} from "./base-moika-entity";

export class Address extends BaseMoikaEntity {
   id_Addr: number;
   street: string;
   building: string;
   letter: string;
   latitude: number;
   longitude: number;
   idCity: number;
   city: City;


}
