import {Address} from "./address";
import {User} from "./user";
import {WashBox} from "./wash-box";
import {BaseMoikaEntity} from "./base-moika-entity";
import {Phone} from "./phone";

export class WashFacility extends BaseMoikaEntity {
   id: number;
   idNet: number;
   name: string;
   description: string;
   idAddr:  number;
   phones: Array<Phone>;
   facilityAddr: Address;
   idManager: number;
   manager: User;
   washBoxes: Array<WashBox>;

}
