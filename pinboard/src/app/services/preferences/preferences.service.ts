import { Injectable } from '@angular/core';
import { Preference } from 'src/app/models/preference';
import { PreferenceType } from 'src/app/models/preferenceType';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PreferencesService {

  constructor(private http: HttpClient) { }

  public getPreferences(): Observable<Preference[]> {
    return this.http.get<Preference[]>(environment.preferenceServiceUrl + '/owner/1', {
      headers: {'Access-Control-Allow-Origin':'*'}
    });
    // return [
    //   { preferenceId: 1, value: '#aaaaaa', userId: 1, preferenceType: { preferenceTypeId: 1, name: "Color" }, boolValue: null },
    //   { preferenceId: 2, value: null, userId: 1, preferenceType: { preferenceTypeId: 2, name: "Is user online"}, boolValue: false },
    //   { preferenceId: 3, value: null, userId: 1, preferenceType: { preferenceTypeId: 2, name: "Is email confirmed"}, boolValue: true },
    //   { preferenceId: 4, value: null, userId: 1, preferenceType: { preferenceTypeId: 2, name: "Is user authenticated"}, boolValue: false },
    //   { preferenceId: 5, value: 'Kenan', userId: 1, preferenceType: { preferenceTypeId: 2, name: "Authenticated admin"}, boolValue: null }
    // ];
  }

  public saveChanges(preferences: Preference[]) {
    return this.http.post(environment.preferenceServiceUrl + '/batch', preferences,
    );
  }
}
