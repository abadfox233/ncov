package cn.yogehaoren.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WangNing yogehaoren@gamil.com <br>
 * @since 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public  class Config{
    private String studentId;
    private String password;
    private SubmitMessage submitMessage;
}