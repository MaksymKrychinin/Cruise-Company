import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {TcDesignSystemModule} from "tc-design-system";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    TcDesignSystemModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
