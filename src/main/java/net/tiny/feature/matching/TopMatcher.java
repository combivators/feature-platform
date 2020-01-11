package net.tiny.feature.matching;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import net.tiny.feature.matching.api.Entry;
import net.tiny.feature.matching.api.Settings;

@Path("/v1/api/top")
public class TopMatcher {

    @GET
    @Path("query?{n=\\d+}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public List<Entry> top(@QueryParam("n")int num) {
        List<Entry> entries = new ArrayList<>();
        final int size = Math.max(Math.max(num,  20), Math.min(num,  99));
        for (int i=0; i<size; i++) {
            entries.add(generate());
        }
        return entries;
    }

    @GET
    @Path("setting?{_=\\d+}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Settings setting(@QueryParam("_")String id) {
        Settings settings = new Settings();
        settings.entries = generator();
        return settings;
    }


    @GET
    @Path("search/{query}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Settings search(@PathParam("query")String query) {
        Settings settings = new Settings();
        settings.entries = generator();
        return settings;
    }

    private List<Entry> generator() {
        List<Entry> entries = new ArrayList<>();
        for (int i=0; i<50; i++) {
            entries.add(generate());
        }
        return entries;
    }

    protected Entry generate() {
        int size = ThreadLocalRandom.current().nextInt(30, 64);
        int id = ThreadLocalRandom.current().nextInt(10000, 19999);
        Entry entry = new Entry();
        entry.width = size;
        entry.height = size;
        entry.image = "/img/" + icons[ThreadLocalRandom.current().nextInt(0, 41)];
        entry.url = entry.url + id;
        entry.tooltip = "Hoge";
        return entry;
    }

    static String[] icons = new String[] {
        "ic_account.svg",
        "ic_amazon.svg",
        "ic_baidu.svg",
        "ic_boy_blue.svg",
        "ic_canon.svg",
        "ic_circle.svg",
        "ic_cloud.svg",
        "ic_facebook.svg",
        "ic_girl_blue.svg",
        "ic_github.svg",
        "ic_google.svg",
        "ic_info.svg",
        "ic_line.svg",
        "ic_linkedin.svg",
        "ic_magento.svg",
        "ic_messenge.svg",
        "ic_office_boy.svg",
        "ic_office_customer_m.svg",
        "ic_office_female.svg",
        "ic_office_girl.svg",
        "ic_office_helper_f.svg",
        "ic_office_helper_m.svg",
        "ic_office_male.svg",
        "ic_office_unknown_f.svg",
        "ic_office_unknown_m.svg",
        "ic_qq.svg",
        "ic_question.svg",
        "ic_slideshare.svg",
        "ic_tackoverflow.svg",
        "ic_telegram.svg",
        "ic_twitter.svg",
        "ic_user_avatar.svg",
        "ic_user_blue.svg",
        "ic_user_female.svg",
        "ic_user_female2.svg",
        "ic_user_green.svg",
        "ic_user_male.svg",
        "ic_user_male2.svg",
        "ic_user_while.svg",
        "ic_webchat.svg",
        "ic_whatsapp.svg",
        "ic_yahoo.svg"
    };
}
