package pl.molasym.photoGrade.VO;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Created by moliq on 23.10.16.
 */
public class Login {

    @NotNull
    @Length(min = 6)
    private String nickname;

    @NotNull
    @Length(min = 8)
    private String password;

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
