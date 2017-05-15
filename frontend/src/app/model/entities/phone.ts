import {BaseMoikaEntity} from "./base-moika-entity";
export class Phone extends BaseMoikaEntity {
  private _idPhone: number;
  private _phoneNumber: string;

  get idPhone(): number {
    return this._idPhone;
  }

  set idPhone(value: number) {
    this._idPhone = value;
  }

  get phoneNumber(): string {
    return this._phoneNumber;
  }

  set phoneNumber(value: string) {
    this._phoneNumber = value;
  }
}
