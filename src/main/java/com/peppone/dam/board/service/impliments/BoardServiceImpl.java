package com.peppone.dam.board.service.impliments;

import static com.peppone.dam.exception.ErrorCode.BOARD_NAME_DUPLICATED;
import static com.peppone.dam.exception.ErrorCode.BOARD_NOT_FOUND;
import static com.peppone.dam.exception.ErrorCode.BOARD_URL_DUPLICATED;
import static com.peppone.dam.exception.ErrorCode.NOT_ALLOWED;
import static com.peppone.dam.exception.ErrorCode.USER_NOT_FOUND;
import static com.peppone.dam.post.domain.PostType.POST_TYPE_GENERAL;
import static com.peppone.dam.post.domain.PostType.POST_TYPE_NOTICE;
import static com.peppone.dam.post.domain.PostType.POST_TYPE_PINNED;

import com.peppone.dam.board.domain.BoardEntity;
import com.peppone.dam.board.domain.BoardType;
import com.peppone.dam.board.dto.BoardEditDto;
import com.peppone.dam.board.dto.BoardListDto;
import com.peppone.dam.board.dto.BoardMakingDto;
import com.peppone.dam.board.repository.BoardRepository;
import com.peppone.dam.board.service.BoardService;
import com.peppone.dam.exception.CustomException;
import com.peppone.dam.post.domain.OrderType;
import com.peppone.dam.post.domain.PostType;
import com.peppone.dam.post.dto.ReadPostDto;
import com.peppone.dam.post.repository.PostRepository;
import com.peppone.dam.response.CommonResponse;
import com.peppone.dam.response.ResponseService;
import com.peppone.dam.user.domain.UserEntity;
import io.micrometer.common.util.StringUtils;
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
public class BoardServiceImpl implements BoardService {

  private final BoardRepository boardRepository;
  private final ResponseService responseService;
  private final PostRepository postRepository;

  @Transactional
  @Override
  public CommonResponse makeBoard(BoardMakingDto boardMakingDto, UserEntity user) {
    boolean boardExistByName = boardRepository.existsByName(boardMakingDto.getName());
    boolean boardExistByUrl = boardRepository.existsByUrl(boardMakingDto.getUrl());

    if (boardExistByName) {
      throw new CustomException(BOARD_NAME_DUPLICATED);
    }

    if (boardExistByUrl) {
      throw new CustomException(BOARD_URL_DUPLICATED);
    }

    if (user == null) {
      throw new CustomException(USER_NOT_FOUND);
    }

    BoardEntity makeBoard = BoardEntity.builder()
        .name(boardMakingDto.getName())
        .url(boardMakingDto.getUrl())
        .boardType(BoardType.BOARD_TYPE_MINI)
        .adminId(user)
        .build();

    boardRepository.save(makeBoard);

    return responseService.getSingleResponse(BoardListDto.from(makeBoard));
  }

  @Override
  public CommonResponse getBoardList() {
    List<BoardListDto> list = boardRepository.findAll().stream().map(BoardListDto::from).toList();
    return responseService.getListResponse(list);
  }

  @Override
  public CommonResponse getBoardListByType(BoardType boardType) {

    List<BoardListDto> list = boardRepository.findAllByBoardType(boardType).stream()
        .map(BoardListDto::from).toList();

    return responseService.getListResponse(list);
  }

  @Override
  public CommonResponse getBoardPostList(String id, long page, long size, OrderType order,
      boolean orderDirection, Pageable pageable) {

    PageRequest pageRequest = null;

    if (orderDirection) {
      pageRequest = PageRequest.of((int) page, (int) size,
          Sort.by(order.toString()).descending());
    } else {
      pageRequest = PageRequest.of((int) page, (int) size,
          Sort.by(order.toString()).ascending());
    }

    if (!boardRepository.existsByUrl(id)) {
      return responseService.ErrorResponse(BOARD_NOT_FOUND);
    }

    BoardEntity board = boardRepository.findByUrl(id);

    List<ReadPostDto> posts = getPostByType(board, pageRequest, POST_TYPE_GENERAL);

    return responseService.getListResponse(posts);
  }

  @Override
  public CommonResponse getBoardPinnedPostList(String id) {
    if (!boardRepository.existsByUrl(id)) {
      throw new CustomException(BOARD_NOT_FOUND);
    }

    BoardEntity board = boardRepository.findByUrl(id);

    List<ReadPostDto> posts = getPostByType(board, PageRequest.of(0, 3,
        Sort.by("id").descending()), POST_TYPE_PINNED);

    return responseService.getListResponse(posts);
  }

  @Override
  public CommonResponse getBoardNoticePostList(String id) {
    if (!boardRepository.existsByUrl(id)) {
      throw new CustomException(BOARD_NOT_FOUND);
    }

    BoardEntity board = boardRepository.findByUrl(id);

    List<ReadPostDto> posts = getPostByType(board, PageRequest.of(0, 3,
        Sort.by("id").descending()), POST_TYPE_NOTICE);

    return responseService.getListResponse(posts);
  }

  @Transactional
  @Override
  public CommonResponse editBoard(BoardEditDto boardEditDto, UserEntity user) {

    BoardEntity board = boardRepository.findById(boardEditDto.getId()).orElseThrow();

    if (user == null) {
      throw new CustomException(USER_NOT_FOUND);
    }

    if (!user.getRole().get(0).equals("ROLE_ADMIN")) {
      throw new CustomException(NOT_ALLOWED);
    }

    if (StringUtils.isNotBlank(boardEditDto.getName())) {
      board.setName(boardEditDto.getName());
    }

    if (StringUtils.isNotBlank(boardEditDto.getUrl())) {
      board.setUrl(boardEditDto.getUrl());
    }

    if (StringUtils.isNotBlank(boardEditDto.getBoardType().name())) {
      board.setBoardType(boardEditDto.getBoardType());
    }

    boardRepository.save(board);

    BoardListDto response = BoardListDto.from(board);

    return responseService.getSingleResponse(response);
  }

  private List<ReadPostDto> getPostByType(BoardEntity board, PageRequest pageRequest,
      PostType postType) {
    return postRepository
        .findAllByBoardIdAndPostTypeAndAccessTrue(board, pageRequest, postType)
        .stream().map(ReadPostDto::from).toList();
  }
}