import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs/Subscription";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-facility-main',
  templateUrl: './facility-main.component.html',
  styleUrls: ['./facility-main.component.css']
})
export class FacilityMainComponent  implements OnDestroy {

private routeSubscription: Subscription;

  constructor(private route: ActivatedRoute){

    this.routeSubscription = route.params.subscribe();
  }
  ngOnDestroy(){
    this.routeSubscription.unsubscribe();
  }

}
