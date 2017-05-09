import {BaseMoikaEntity} from "./base-moika-entity";

export class City extends BaseMoikaEntity {
  private _idCity: number;
  private _name: string;
  private _region: string;

  get idCity(): number {
    return this._idCity;
  }

  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
  }

  get region(): string {
    return this._region;
  }

  set region(value: string) {
    this._region = value;
  }
}
