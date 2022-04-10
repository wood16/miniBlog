package com.bookshop.poc.service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.lucene.analysis.core.KeywordTokenizerFactory;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.standard.StandardFilterFactory;
import org.hibernate.search.annotations.*;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Indexed
@Entity
@Table(name = "tbl_book")
@AnalyzerDef(name = "bookAnalyzer",
        tokenizer = @TokenizerDef(factory = KeywordTokenizerFactory.class ),
        filters = {
                @TokenFilterDef(factory = StandardFilterFactory.class),
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
        }
)
public class BookEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Field(analyzer = @Analyzer(definition = "bookAnalyzer"))
    @Column(name = "title")
    private String title;

    @Field(analyzer = @Analyzer(definition = "bookAnalyzer"))
    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "image", columnDefinition = "text")
    private String image;

    @Field(analyzer = @Analyzer(definition = "bookAnalyzer"))
    @Column(name = "category")
    private String category;

    @Column(name = "create_date")
    private String createDate;

    @Column(name = "modified_date")
    private String modifiedDate;
}
