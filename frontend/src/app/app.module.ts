// Ангуляровский импорт
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import {Routes, RouterModule} from '@angular/router';

// Имплот Primeng
import {ButtonModule} from 'primeng/primeng';

// Иоплоь полекьа млйка
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/common/header/header.component';
import { LadingComponent } from './components/common/lading/lading.component';
import { AboutComponent } from './components/common/about/about.component';
import { DevMadvComponent } from './components/common/dev-madv/dev-madv.component';
import { NotFoundComponent } from './components/common/not-found/not-found.component';
import { FooterComponent } from './components/common/footer/footer.component';

// определение маршрутов
const appRoutes: Routes =[
  { path: '', component: LadingComponent},
  { path: 'dev-madv', component: DevMadvComponent},
  { path: 'about', component: AboutComponent},
  { path: '**', component: NotFoundComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LadingComponent,
    AboutComponent,
    DevMadvComponent,
    NotFoundComponent,
    FooterComponent
  ],
  imports: [BrowserModule, FormsModule, HttpModule, RouterModule.forRoot(appRoutes),
    ButtonModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
