
import {BaseMoikaEntity} from "./base-moika-entity";

export class SomeType extends BaseMoikaEntity {
  private _idType: number;
  private _typeCode: string;
  private _typeName: string;
  private _description: string;

  get id(): number {
    return this._idType;
  }

  set id(value: number) {
    this._idType = value;
  }

  get typeCode(): string {
    return this._typeCode;
  }

  set typeCode(value: string) {
    this._typeCode = value;
  }

  get typeName(): string {
    return this._typeName;
  }

  set typeName(value: string) {
    this._typeName = value;
  }

  get description(): string {
    return this._description;
  }

  set description(value: string) {
    this._description = value;
  }
}
