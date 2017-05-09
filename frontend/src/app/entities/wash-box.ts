import {BaseMoikaEntity} from "./base-moika-entity";
/*
 Wash Box FrontEnd REST entity
 */
export class WashBox extends BaseMoikaEntity {
  private _id: number;
  private _idFacility: number;
  private _boxName: string;
  private _description: string;
  private _idType: number;
  private _idStatus: number;


  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get idFacility(): number {
    return this._idFacility;
  }

  set idFacility(value: number) {
    this._idFacility = value;
  }

  get boxName(): string {
    return this._boxName;
  }

  set boxName(value: string) {
    this._boxName = value;
  }

  get description(): string {
    return this._description;
  }

  set description(value: string) {
    this._description = value;
  }

  get idType(): number {
    return this._idType;
  }

  set idType(value: number) {
    this._idType = value;
  }

  get idStatus(): number {
    return this._idStatus;
  }

  set idStatus(value: number) {
    this._idStatus = value;
  }
}
