import {BaseMoikaEntity} from "./base-moika-entity";
import {SomeStatus} from "./some-status";
import {SomeType} from "./some-type";
import {BoxOddDate} from "./box-odd-date";
import {BoxWeekDay} from "./box-week-day";
/*
 Wash Box FrontEnd REST entity
 */
export class WashBox extends BaseMoikaEntity {
   id: number;
   idFacility: number;
   boxName: string;
   description: string;
   idType: number;
   boxTypeEntity: SomeType;
   idStatus: number;
   boxStatusEntity: SomeStatus;
   timeTable:  Array<BoxOddDate>;
   weekTimeTable:  Array<BoxWeekDay>;


   fromJsonObj(jsonObj :any){
     //  console.log("fromJsonObj - box");
     //  console.log(jsonObj);
     this.id = jsonObj.id;
     this.idFacility =  jsonObj.idFacility;
     this.boxName = jsonObj.boxName ;
     this.description = jsonObj.description;
     this.idType = jsonObj.idType;
     this.boxTypeEntity = jsonObj.boxTypeEntity;
    // console.log("assign Boxes-type");
     this.idStatus = jsonObj.idStatus ;
     this.boxStatusEntity = jsonObj.boxStatusEntity
    // console.log("assign Boxes-status");

     this.timeTable = new Array<BoxOddDate>();
     jsonObj.timeTable.forEach(oddDate => {
       let _d : BoxOddDate = new BoxOddDate();
       _d.fromJsonObj(oddDate);
       this.timeTable.push(_d);
     });
    // console.log("assigned Boxes-oddDates");

     this.weekTimeTable = new Array<BoxWeekDay>();
     jsonObj.weekTimeTable.forEach(weekDay => {
       let _d : BoxWeekDay = new BoxWeekDay();
       _d.fromJsonObj(weekDay);
       this.weekTimeTable.push(_d);
     });
   //  console.log("assigned Boxes-weekDays");
   }

}
