package com.example.blogapp.service;

import com.example.blogapp.entity.BlogPost;
import com.example.blogapp.entity.PostTags;
import com.example.blogapp.entity.Tags;
import com.example.blogapp.repository.BlogPostRepo;
import com.example.blogapp.repository.PostTagsRepo;
import com.example.blogapp.repository.TagsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {


    @Autowired
    private BlogPostRepo blogPostRepo;

    @Autowired
    public TagsRepo tagsRepo;

    @Autowired
    public PostTagsRepo postTagsRepo;

    public List<BlogPost> getAllEmployees() {
        return blogPostRepo.findAll();
    }


    public Page<BlogPost> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.blogPostRepo.findAll(pageable);
    }

    public Page<BlogPost> findPaginated2(int pageNo, int pageSize, String sortField, String sortDirection,String filtername) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.blogPostRepo.findAllByName(pageable,filtername);
    }

    public String create(String name, String email, String title, String content, String excerpt) {
        try{
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = f.format(new Date());
            BlogPost blogPost = new BlogPost(name, email, title, content, 0, time, excerpt);
            blogPostRepo.save(blogPost);
            String allTags[] = excerpt.split(",");
            for(String tag: allTags){
                Tags tags = new Tags(tag,time,time);
                tagsRepo.save(tags);
                int tagId = tags.getId();
                PostTags postTags = new PostTags(tagId,time,time);
                postTagsRepo.save(postTags);
            }
            return "Inserted";
        }
        catch (Exception e){
            return "Unable to Insert";
        }
    }

    public List<BlogPostDTO> retrieve() {
        List<BlogPostDTO> blogPostDTOS = new LinkedList<>();
        List<BlogPost> list = blogPostRepo.findAll();
        list.forEach(blogPost -> {
            BlogPostDTO blogPostDTO = new BlogPostDTO();
            blogPostDTO.setId(blogPost.getId());
            blogPostDTO.setAuthorname(blogPost.getAuthorname());
            blogPostDTO.setBlogcontent(blogPost.getBlogcontent());
            blogPostDTO.setEmail(blogPost.getEmail());
            blogPostDTO.setTitle(blogPost.getTitle());
            blogPostDTO.setExcerpt(blogPost.getExcerpt());
            blogPostDTO.setTime(blogPost.getTime());
            List<CommentDTO> commentDTOS = new ArrayList<>();
            blogPost.getCommentList().forEach(comment -> {
                CommentDTO commentDTO = new CommentDTO();
                commentDTO.setCommentby(comment.getCommentby());
                commentDTO.setComment(comment.getComment());
                commentDTO.setId(comment.getId());
                commentDTOS.add(commentDTO);
            });
            blogPostDTO.setComments(commentDTOS);
            blogPostDTOS.add(blogPostDTO);
        });
        return blogPostDTOS;
    }

    public String update(String id, String content) {
        try{
            int blogId = Integer.parseInt(id);
            blogPostRepo.updateblogs(content,blogId);
            return "updated";
        }
        catch (Exception e){
            return "Cannot Update";
        }
    }

    public String delete(String id) {
        try{
            int blogId = Integer.parseInt(id);
            blogPostRepo.deleteById(blogId);
            return "Deleted";
        }
        catch (Exception e){
            return "Cannot Delete";
        }
    }

    public List<BlogPostDTO> find(String id) {
        int blogId = Integer.parseInt(id);
        List<BlogPostDTO> blogPostDTOS = new LinkedList<>();
        List<BlogPost> list = blogPostRepo.findAllById(blogId);
        list.forEach(blogPost -> {
            BlogPostDTO blogPostDTO = new BlogPostDTO();
            blogPostDTO.setId(blogPost.getId());
            blogPostDTO.setAuthorname(blogPost.getAuthorname());
            blogPostDTO.setBlogcontent(blogPost.getBlogcontent());
            blogPostDTO.setEmail(blogPost.getEmail());
            blogPostDTO.setTitle(blogPost.getTitle());
            blogPostDTO.setExcerpt(blogPost.getExcerpt());
            blogPostDTO.setTime(blogPost.getTime());
            List<CommentDTO> commentDTOS = new ArrayList<>();
            blogPost.getCommentList().forEach(comment -> {
                CommentDTO commentDTO = new CommentDTO();
                commentDTO.setCommentby(comment.getCommentby());
                commentDTO.setComment(comment.getComment());
                commentDTO.setId(comment.getId());
                commentDTOS.add(commentDTO);
            });
            blogPostDTO.setComments(commentDTOS);
            blogPostDTOS.add(blogPostDTO);
        });
        return blogPostDTOS;
    }

    public List<BlogPost> search(String keyword) {
        List<BlogPost> list = blogPostRepo.search(keyword);
        return list;
    }

    public List<BlogPostDTO> filter(String name, String content, String excerpt) {
        List<BlogPostDTO> blogPostDTOS = new LinkedList<>();
        List<BlogPost> list = blogPostRepo.filter(name, content, excerpt);
        list.forEach(blogPost -> {
            BlogPostDTO blogPostDTO = new BlogPostDTO();
            blogPostDTO.setId(blogPost.getId());
            blogPostDTO.setAuthorname(blogPost.getAuthorname());
            blogPostDTO.setBlogcontent(blogPost.getBlogcontent());
            blogPostDTO.setEmail(blogPost.getEmail());
            blogPostDTO.setTitle(blogPost.getTitle());
            blogPostDTO.setExcerpt(blogPost.getExcerpt());
            blogPostDTO.setTime(blogPost.getTime());
            List<CommentDTO> commentDTOS = new ArrayList<>();
            blogPost.getCommentList().forEach(comment -> {
                CommentDTO commentDTO = new CommentDTO();
                commentDTO.setCommentby(comment.getCommentby());
                commentDTO.setComment(comment.getComment());
                commentDTO.setId(comment.getId());
                commentDTOS.add(commentDTO);
            });
            blogPostDTO.setComments(commentDTOS);
            blogPostDTOS.add(blogPostDTO);
        });
        return blogPostDTOS;
    }

    public List<BlogPost> filterAndSearch(String keyword, String name, String content, String excerpt) {
        return blogPostRepo.filterAndSearch(keyword,name,content,excerpt);
    }

    public List<BlogPostDTO> paginated(int page, String sort, String direction, String limit) {
        int pageSize = Integer.parseInt(limit);
        Page<BlogPost> pageObj = findPaginated(page, pageSize, sort, direction);
        List<BlogPost> list = pageObj.getContent();
        List<BlogPostDTO> blogPostDTOS = new LinkedList<>();
        list.forEach(blogPost -> {
            BlogPostDTO blogPostDTO = new BlogPostDTO();
            blogPostDTO.setId(blogPost.getId());
            blogPostDTO.setAuthorname(blogPost.getAuthorname());
            blogPostDTO.setBlogcontent(blogPost.getBlogcontent());
            blogPostDTO.setEmail(blogPost.getEmail());
            blogPostDTO.setTitle(blogPost.getTitle());
            blogPostDTO.setExcerpt(blogPost.getExcerpt());
            blogPostDTO.setTime(blogPost.getTime());
            List<CommentDTO> commentDTOS = new ArrayList<>();
            blogPost.getCommentList().forEach(comment -> {
                CommentDTO commentDTO = new CommentDTO();
                commentDTO.setCommentby(comment.getCommentby());
                commentDTO.setComment(comment.getComment());
                commentDTO.setId(comment.getId());
                commentDTOS.add(commentDTO);
            });
            blogPostDTO.setComments(commentDTOS);
            blogPostDTOS.add(blogPostDTO);
        });
        return blogPostDTOS;
    }

    public String guestupdate(String id, String email, String content) {
        try{
            int blogId = Integer.parseInt(id);
            if(email.equals(blogPostRepo.email(blogId)))
                blogPostRepo.updateblogs(content,blogId);
            else
                return "Cannot Update";
            return "updated";
        }
        catch (Exception e){
            return "Cannot Update";
        }
    }

    public String guestdelete(String id, String email) {
        try{
            int blogId = Integer.parseInt(id);
            if(email.equals(blogPostRepo.email(blogId)))
                blogPostRepo.deleteById(blogId);
            else
                return "Cannot Delete";
            return "Deleted";
        }
        catch (Exception e){
            return "Cannot Delete";
        }
    }

    public List<BlogPostDTO> myblog(String email) {
        List<BlogPostDTO> blogPostDTOS = new LinkedList<>();
        List<BlogPost> list = blogPostRepo.findAllByEmail(email);
        list.forEach(blogPost -> {
            BlogPostDTO blogPostDTO = new BlogPostDTO();
            blogPostDTO.setId(blogPost.getId());
            blogPostDTO.setAuthorname(blogPost.getAuthorname());
            blogPostDTO.setBlogcontent(blogPost.getBlogcontent());
            blogPostDTO.setEmail(blogPost.getEmail());
            blogPostDTO.setTitle(blogPost.getTitle());
            blogPostDTO.setExcerpt(blogPost.getExcerpt());
            blogPostDTO.setTime(blogPost.getTime());
            List<CommentDTO> commentDTOS = new ArrayList<>();
            blogPost.getCommentList().forEach(comment -> {
                CommentDTO commentDTO = new CommentDTO();
                commentDTO.setCommentby(comment.getCommentby());
                commentDTO.setComment(comment.getComment());
                commentDTO.setId(comment.getId());
                commentDTOS.add(commentDTO);
            });
            blogPostDTO.setComments(commentDTOS);
            blogPostDTOS.add(blogPostDTO);
        });
        return blogPostDTOS;
    }
}
