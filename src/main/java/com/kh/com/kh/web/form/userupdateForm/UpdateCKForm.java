package com.kh.com.kh.web.form.userupdateForm;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateCKForm {
  @Size(min=2,max=8, message = "2~8 글자까지 가능합니다")
  private String nickname;
  @Size
  private String tel;
  private String passwd;
  private Long member_id;
}
