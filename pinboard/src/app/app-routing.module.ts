import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { WorkspaceComponent } from './components/workspace/workspace.component';
import { OptionsComponent } from './components/options/options.component';

const routes: Routes = [
  { path: 'workspace', component: WorkspaceComponent },
  { path: 'login', component: LoginComponent },
  { path: 'options', component: OptionsComponent },
  { path: '', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
