package com.example.javaspringtbootjpa.model;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.*;
import lombok.Data;

/** Rating of a Tour by a Customer */
@Entity
@Table(name = "tour_rating")
@Data
public class TourRating {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "tour_id")
  private Tour tour;

  @Column(name = "customer_id")
  private Integer customerId;

  @Column(nullable = false)
  private Integer score;

  @Column
  private String comment;

  protected TourRating() {}

  /**
   * Create a fully initialized TourRating.
   *
   * @param tour the tour.
   * @param customerId the customer identifier.
   * @param score Integer score (1-5)
   * @param comment Optional comment from the customer
   */
  public TourRating(Tour tour, Integer customerId, Integer score, String comment) {
    this.tour = tour;
    this.customerId = customerId;
    this.score = score;
    this.comment = comment;
  }

  /**
   * Create a fully initialized TourRating.
   *
   * @param tour the tour.
   * @param customerId the customer identifier.
   * @param score Integer score (1-5)
   */
  public TourRating(Tour tour, Integer customerId, Integer score) {
    this.tour = tour;
    this.customerId = customerId;
    this.score = score;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public Integer getCustomerId() {
    return customerId;
  }
}
