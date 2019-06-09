import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Post } from 'src/app/models/post';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AddNoteComponent } from '../modals/add-note/add-note/add-note.component';
import { PostService } from 'src/app/services/post/post.service';
import { WorkspaceService } from 'src/app/services/workspace/workspace.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  @Input()
  public content: Post;

  @Input()
  public postColor: string;

  constructor(private modalService: NgbModal, private postService: PostService, private workspaceService: WorkspaceService) { }

  ngOnInit() {
  }

  openEditModal() {
    const modalRef = this.modalService.open(AddNoteComponent);
    modalRef.componentInstance.title = this.content.title;
    modalRef.componentInstance.content = this.content.content;
    modalRef.componentInstance.id = this.content.id;
  }

  removePost() {
    this.postService.removePost(this.content.id);
    this.workspaceService.removePosts(this.content.id);
  }
  
}
