import {Address} from "./address";
import {User} from "./user";
import {WashBox} from "./wash-box";
import {BaseMoikaEntity} from "./base-moika-entity";

export class WashFacility extends BaseMoikaEntity {
   id: number;
   idNet: number;
   name: string;
   description: string;
   idAddr:  number;
   facilityAddr: Address;
   idManager: number;
   manager: User;
   washBoxes: Array<WashBox>;

}
