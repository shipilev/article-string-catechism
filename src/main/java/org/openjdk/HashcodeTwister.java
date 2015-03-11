package org.openjdk;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(5)
@State(Scope.Thread)
public class HashcodeTwister {

    private String str1;
    private String str2;

    /*
        "прокомментировавшей двенадцатимегапиксельном",
        "свеженакиданный канцелярскобюрократический",
        "французишкам механические средства труда",
        "срежиссированное высокоинквизиторством",
        "перещелкивают деперсонализированными",
        "рентгенокинематография прославленым",
        "неврастениками непроанализированная",
        "лжеотождествление электровиолончели",
        "саморастягивающийся антиобаятельно",
        "пятидесятигаллоновой разцентрована",
        "противоестесственно поплевывающие",
        "перекрутанувшись великомученникам",
        "эмульгированного неузнаваемостью",
        "инплантированные смертоносностию",
        "закупориваемся эксцентриситетами",
        "помастурбировать двадцатитрехног",
        "многозначней супертранспортников",
        "инплантированные смертоносностию",
        "забаррикадировавший натуропатами",
        "сгибательницей скомпенсировался",
        "подрезанное перпендикулярностью",
        "сверхинструментом пренебрегшая",
        "полисклеротическая вагнеровата",
        "самоизнасилованное восхищающей",
        "проносящее чернокомбинезонника",
        "восьмидесятипяткой коммутирует",
        "взаимозамкнутая фетишированием",
        "самоокупаемый комментаторский",
        "прокукарекано непрекословными",
        "подпоясывавшей сверхскопление",
        "европеизированные препроверки",
        "неприконченные староизраильцы",
        "спрутоподобного наблевавшись",
        "обошедшегося интеллигенциями",
        "обогащайте новоалександрийцы",
        "янтаринкой среднеискушенного",
        "сцентрированной перцовочкой",
        "обдолбавшимися макдональдса",
        "безупречней стопроцентности",
        "автовариант финдепартамента",
        "эсператистами малоосязаема",
        "чаеотправителей балаклава",
        "убалтывают нереализуемыми",
        "трёхэтажный осведомлённее",
     */

    @Setup
    public void setup() {
        str1 = "лжеотождествление электровиолончели";
        str2 = "электровиолончели лжеотождествление";
    }

    @Benchmark
    public int test1() {
        return str1.hashCode();
    }

    @Benchmark
    public int test2() {
        return str2.hashCode();
    }

}
