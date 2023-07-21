package com.peppone.dam.service.Impliments;

import com.peppone.dam.domain.BoardEntity;
import com.peppone.dam.domain.PostEntity;
import com.peppone.dam.domain.PostEntity.PostEntityBuilder;
import com.peppone.dam.domain.UserEntity;
import com.peppone.dam.dto.CreatePostDto;
import com.peppone.dam.exception.ErrorCode;
import com.peppone.dam.repository.BoardRepository;
import com.peppone.dam.repository.PostRepository;
import com.peppone.dam.repository.UserRepository;
import com.peppone.dam.response.CommonResponse;
import com.peppone.dam.response.ResponseService;
import com.peppone.dam.service.PostService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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


  @Transactional
  @Override
  public CommonResponse createPost(UserEntity user, CreatePostDto createPostDto) {
    if (userRepository.findByUserEmail(user.getUserEmail()) == null) {
      return responseService.ErrorResponse(ErrorCode.USER_NOT_FOUND);
    }

    if (!boardRepository.existsById(createPostDto.getBoardId())) {
      return responseService.ErrorResponse(ErrorCode.BOARD_NOT_FOUND);
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
        .build();

    postRepository.save(post);



    return responseService.getSingleResponse(post);
  }
}
