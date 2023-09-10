package com.supercoding.hanyipman.controller;


import com.supercoding.hanyipman.dto.Shop.buyer.response.ShopInfoResponse;
import com.supercoding.hanyipman.dto.reivew.request.ViewShopReviewsRequest;
import com.supercoding.hanyipman.dto.reivew.response.ViewShopReviewsResponse;
import com.supercoding.hanyipman.dto.Shop.buyer.request.ViewShopListRequest;
import com.supercoding.hanyipman.dto.Shop.buyer.response.ViewShopListResponse;
import com.supercoding.hanyipman.dto.user.CustomUserDetail;
import com.supercoding.hanyipman.dto.vo.Response;
import com.supercoding.hanyipman.service.BuyerShopService;
import com.supercoding.hanyipman.service.ReviewService;
import com.supercoding.hanyipman.utils.ApiUtils;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/buyer-shops")
@RestController
@Slf4j
@RequiredArgsConstructor
@Api(tags = "소비자 가게 관련 API")
public class BuyerShopController {

    private final BuyerShopService buyerShopService;
    private final ReviewService reviewService;

    @GetMapping(value = "", headers = "X-API-VERSION=1")
    public Response<ViewShopListResponse> findShopList(ViewShopListRequest viewShopListRequest,
                                                       @AuthenticationPrincipal CustomUserDetail customUserDetail) {

        return ApiUtils.success(HttpStatus.OK, "리스트 조회 성공", buyerShopService.findShopList(viewShopListRequest, customUserDetail));
    }

    @GetMapping(value = "/{shop_id}/info", headers = "X-API-VERSION=1")
    public Response<ShopInfoResponse> findShopDetail(@PathVariable(value = "shop_id") Long shopId) {

        return ApiUtils.success(HttpStatus.OK, "가게 정보 조회 성공", buyerShopService.findShopDetail(shopId));
    }

    @GetMapping(value = "/{shopId}/reviews", headers = "X-API-VERSION=1")
    public Response<ViewShopReviewsResponse> viewShopReviews(@PathVariable String shopId,
                                                             @RequestBody ViewShopReviewsRequest viewShopReviewsRequest) {

        return ApiUtils.success(HttpStatus.OK, "해당 가게에 대한 리뷰 조회에 성공했습니다.", reviewService.viewShopReviews(shopId, viewShopReviewsRequest));
    }

    @GetMapping(value = "/{shopId}/review-average", headers = "X-API-VERSION=1")
    public Response<Double> viewShopReviewAverage(@PathVariable String shopId) {

        return ApiUtils.success(HttpStatus.OK, "해당 가게의 평균 리뷰 별점 조회에 성공했습니다.", reviewService.viewShopReviewAverage(shopId));
    }

}
