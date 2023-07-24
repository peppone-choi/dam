package com.peppone.dam.post.service.impiments;

import static com.peppone.dam.exception.ErrorCode.BOARD_NOT_FOUND;
import static com.peppone.dam.exception.ErrorCode.POST_ACCESS_IS_DENIED;
import static com.peppone.dam.exception.ErrorCode.POST_NOT_FOUND;
import static com.peppone.dam.exception.ErrorCode.USER_NOT_FOUND;

import com.peppone.dam.board.domain.BoardEntity;
import com.peppone.dam.board.repository.BoardRepository;
import com.peppone.dam.comment.dto.ReadCommentDto;
import com.peppone.dam.comment.repository.CommentRepository;
import com.peppone.dam.exception.CustomException;
import com.peppone.dam.post.domain.PostEntity;
import com.peppone.dam.post.dto.CreatePostDto;
import com.peppone.dam.post.dto.ReadPostDto;
import com.peppone.dam.post.repository.PostRepository;
import com.peppone.dam.post.service.PostService;
import com.peppone.dam.response.CommonResponse;
import com.peppone.dam.response.ResponseService;
import com.peppone.dam.user.domain.UserEntity;
import com.peppone.dam.user.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

  private final UserRepository userRepository;
  private final PostRepository postRepository;
  private final BoardRepository boardRepository;
  private final ResponseService responseService;
  private final CommentRepository commentRepository;

  @Transactional
  @Override
  public CommonResponse createPost(UserEntity user, CreatePostDto createPostDto) {
    if (userRepository.findByUserEmail(user.getUserEmail()) == null) {
      throw new CustomException(USER_NOT_FOUND);
    }

    if (!boardRepository.existsById(createPostDto.getBoardId())) {
      throw new CustomException(BOARD_NOT_FOUND);
    }

    BoardEntity postBoard = boardRepository.findById(createPostDto.getBoardId()).orElseThrow();

    PostEntity post = PostEntity.builder()
        .userId(user)
        .boardId(postBoard)
        .subject(createPostDto.getSubject())
        .content(createPostDto.getContent())
        .createdTime(LocalDateTime.now())
        .like(0)
        .dislike(0)
        .likeRatio(0)
        .access(true)
        .postType(createPostDto.getPostType())
        .build();

    postRepository.save(post);

    return responseService.getSingleResponse(ReadPostDto.from(post));
  }

  @Override
  public CommonResponse readPost(long id) {

    if (!postRepository.existsById(id)) {
      throw new CustomException(POST_NOT_FOUND);
    }

    if (!postRepository.findById(id).orElseThrow().isAccess()) {
      throw new CustomException(POST_ACCESS_IS_DENIED);
    }

    PostEntity post = postRepository.findById(id).orElseThrow();

    return responseService.getSingleResponse(ReadPostDto.from(post));
  }

  @Override
  public CommonResponse readPostComment(long id, long page, long size, Pageable pageable) {
    if (!postRepository.existsById(id)) {
      throw new CustomException(POST_NOT_FOUND);
    }

    if (!postRepository.findById(id).orElseThrow().isAccess()) {
      throw new CustomException(POST_ACCESS_IS_DENIED);
    }

    PostEntity post = postRepository.findById(id).orElseThrow();

    PageRequest pageRequest = PageRequest.of((int) page, (int) size,
        Sort.by("createdDate").ascending());

    List<ReadCommentDto> comments = commentRepository
        .findAllByPostId(post, pageRequest)
        .stream().map(ReadCommentDto::from).toList();

    return responseService.getListResponse(comments);
  }
}


