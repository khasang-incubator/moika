import {City} from "./city";
import {BaseMoikaEntity} from "./base-moika-entity";

export class Address extends BaseMoikaEntity {
  private _id_Addr: number;
  private _street: string;
  private _building: string;
  private _letter: string;
  private _latitude: number;
  private _longitude: number;
  private _idCity: number;
  private _city: City;


  get id_Addr(): number {
    return this._id_Addr;
  }

  set id_Addr(value: number) {
    this._id_Addr = value;
  }

  get street(): string {
    return this._street;
  }

  set street(value: string) {
    this._street = value;
  }

  get building(): string {
    return this._building;
  }

  set building(value: string) {
    this._building = value;
  }

  get letter(): string {
    return this._letter;
  }

  set letter(value: string) {
    this._letter = value;
  }

  get latitude(): number {
    return this._latitude;
  }

  set latitude(value: number) {
    this._latitude = value;
  }

  get longitude(): number {
    return this._longitude;
  }

  set longitude(value: number) {
    this._longitude = value;
  }

  get idCity(): number {
    return this._idCity;
  }

  set idCity(value: number) {
    this._idCity = value;
  }

  get city(): City {
    return this._city;
  }

  set city(value: City) {
    this._city = value;
  }
}
