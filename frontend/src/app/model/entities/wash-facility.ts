import {Address} from "./address";
import {User} from "./user";
import {WashBox} from "./wash-box";
import {BaseMoikaEntity} from "./base-moika-entity";
import {Phone} from "./phone";
import {WorkCalendar} from "./work-calendar";
import {WeekDay} from "./week-day";

export class WashFacility extends BaseMoikaEntity {
   id: number;
   idNet: number;
   idManager: number;
   name: string;
   description: string;
   idAddr:  number;
   phones: Array<Phone>;
   facilityAddr: Address;
   manager: User;
   washBoxes: Array<WashBox>;
   oddDates:  Array<WorkCalendar>;
   weekDays:  Array<WeekDay>;


   fromJsonObj(jsonObj :any){
     console.log("fromJsonObj - Facility ");
     console.log(jsonObj);
     this.id = jsonObj.id;
  //   console.log("Facility.id "+this.id);
     this.idNet = jsonObj.idNet;
  //   console.log("Facility.idNet "+this.idNet);
     this.idManager = jsonObj.idManager;
   //  console.log("Facility.idManager "+this.idManager);
     this.name = jsonObj.name;
  //   console.log("Facility.name "+this.name);
     this.description = jsonObj.description;
  //  console.log("Facility.description"+this.description);
     this.phones = jsonObj.phones;
 //    console.log("assign Phone");
     this.idAddr = jsonObj.idAddr;
     this.facilityAddr =  new Address();
     this.facilityAddr = jsonObj.facilityAddr
  //   console.log("assign Address");

     this.washBoxes = new Array<WashBox>();
     jsonObj.washBoxes.forEach(box => {
       let _b : WashBox = new WashBox();
       _b.fromJsonObj(box);
       this.washBoxes.push(_b);
     });
 //    console.log("assigned Facility-Boxes");
 //   console.log(this.washBoxes);

     this.oddDates = new Array<WorkCalendar>();
     jsonObj.oddDates.forEach(oddDate => {
       let _d : WorkCalendar = new WorkCalendar();
       _d.fromJsonObj(oddDate);
       this.oddDates.push(_d);
     });
 //    console.log("assigned Facility-oddDates");
  //   console.log(this.oddDates);

     this.weekDays = new Array<WeekDay>();
     jsonObj.weekDays.forEach(weekDay => {
       let _d : WeekDay = new WeekDay();
       _d.fromJsonObj(weekDay);
       this.weekDays.push(_d);
     });
 //    console.log("assigned Facility-weekDays");
  //   console.log(this.weekDays);
   }
}


