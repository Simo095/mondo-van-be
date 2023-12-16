package simonedangelo.mondovan.Post.Payload;

import simonedangelo.mondovan.Post.Enum.Category;

public record PostsDTO(String title, String text, Category category) {
}
