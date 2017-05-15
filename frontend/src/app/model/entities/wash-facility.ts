import {Address} from "./address";
import {User} from "./user";
import {WashBox} from "./wash-box";
import {BaseMoikaEntity} from "./base-moika-entity";

export class WashFacility extends BaseMoikaEntity {
  private _id: number;
  private _idNet: number;
  private _name: string;
  private _description: string;
  private _idAddr:  number;
  private _address: Address;
  private _idManager: number;
  private _manager: User;
  private _washBoxes: Array<WashBox>;

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get idNet(): number {
    return this._idNet;
  }

  set idNet(value: number) {
    this._idNet = value;
  }

  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
  }

  get description(): string {
    return this._description;
  }

  set description(value: string) {
    this._description = value;
  }

  get idAddr(): number {
    return this._idAddr;
  }

  set idAddr(value: number) {
    this._idAddr = value;
  }

  get address(): Address {
    return this._address;
  }

  set address(value: Address) {
    this._address = value;
  }

  get idManager(): number {
    return this._idManager;
  }

  set idManager(value: number) {
    this._idManager = value;
  }

  get manager(): User {
    return this._manager;
  }

  set manager(value: User) {
    this._manager = value;
  }

  get washBoxes(): Array<WashBox> {
    return this._washBoxes;
  }

  set washBoxes(value: Array<WashBox>) {
    this._washBoxes = value;
  }
}
