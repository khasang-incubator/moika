import {City} from "./city";
import {BaseMoikaEntity} from "./base-moika-entity";
import {Coordinate} from "./coordinate";

export class Address extends BaseMoikaEntity {
   id_Addr: number;
   street: string;
   building: string;
   letter: string;
   coordinate: Coordinate;
   idCity: number;
   city: City;
}
