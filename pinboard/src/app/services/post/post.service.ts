import { Injectable } from '@angular/core';
import { WorkspaceService } from '../workspace/workspace.service';
import { HttpClient } from '@angular/common/http';
import { Post } from 'src/app/models/post';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private workspaceService: WorkspaceService, private http: HttpClient) { }

  public addPost(post: Post) : Observable<any> {
    return this.http.post(environment.postServiceUrl + '/workspace/1', post);
  }
}
