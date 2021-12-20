/**
 * Copyright 2021 José Paumard
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.paumard.jepcafe;

import org.openjdk.jmh.annotations.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.concurrent.TimeUnit;


@Warmup(iterations = 20, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 30, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Fork(value = 3)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class StringConcat {

    @Param({"10"})
    private int size;

    @Param({"1000"})
    private int converted;

    private LocalDate date = LocalDate.of(2021, Month.SEPTEMBER, 17);

    private String[] strings;

    @Setup
    public void init() {
        strings = new String[size];
        for (int index = 100000; index < 100000 + size; index++) {
            strings[index - 100000] = "" + index;
        }
    }

    @Benchmark
    public String concat2() {
        return strings[0] + strings[1];
    }

    @Benchmark
    public String concat4() {
        return strings[0] + strings[1] + strings[2] + strings[3];
    }

    @Benchmark
    public String stringBuilder2() {
        StringBuilder sb = new StringBuilder(1024);
        sb.append(strings[0]).append(strings[1]);
        return sb.toString();
    }

    @Benchmark
    public String stringBuilder4() {
        StringBuilder sb = new StringBuilder(1024);
        sb.append(strings[0]).append(strings[1]).append(strings[2]).append(strings[3]);
        return sb.toString();
    }

    @Benchmark
    public String concat() {
        String result = "";
        for (String s : strings) {
            result += s;
        }
        return result;
    }

    @Benchmark
    public String append() {
        StringBuilder sb = new StringBuilder();
        for (String s : strings) {
            sb.append(s);
        }
        return sb.toString();
    }

    @Benchmark
    public String to_string() {
        return Integer.toString(converted);
    }

    @Benchmark
    public String string_int() {
        return "" + converted;
    }

    @Benchmark
    public String string_integer() {
        return concatInteger(size);
    }

    private String concatInteger(Integer o) {
        return "" + o;
    }

    @Benchmark
    public String int_format() {
        return String.format("%d %d %d %d %d", 123, 234, 345, 456, 567);
    }

    @Benchmark
    public String date_format_1() {
        return String.format("%tY-%tm-%td",
                date, date, date);
    }

    @Benchmark
    public String double_format_5() {
        return String.format("%+7.4f %+7.4f %+7.4f %+7.4f %+7.4f", Math.PI, Math.PI, Math.PI, Math.PI, Math.PI);
    }

    @Benchmark
    public String simple_double_format_5() {
        return String.format("%f %f %f %f %f", Math.PI, Math.PI, Math.PI, Math.PI, Math.PI);
    }

    @Benchmark
    public String string_format_5() {
        return String.format("%s %s %s %s %s", "one", "two", "three", "four", "five");
    }
}
