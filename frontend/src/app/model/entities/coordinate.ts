import {BaseMoikaEntity} from "./base-moika-entity";

export interface Coordinate extends BaseMoikaEntity {
  lat: number;
  lon: number;
}
