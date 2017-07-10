import {Component} from '@angular/core';

@Component({
  selector: 'app-fclt-owner-outlet',
  template: `
    <nav>
      <a routerLink="clients" routerLinkActive="active"></a>
      <a routerLink="about" routerLinkActive="active"></a>
      <a routerLink="facilitiesDash" routerLinkActive="active"></a>
      <a routerLink="facilitiesTable" routerLinkActive="active"></a>
      <a routerLink="washFacilitiesList" routerLinkActive="active"></a>
    </nav>
    <router-outlet></router-outlet>`
})
export class FcltOwnerOutletComponent {


}
