package com.lucas.plantTree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trees")
public class TreeController {

    @Autowired
    private TreeDAO treeDAO;

    @PostMapping
    public void createTree(@RequestBody Tree tree) {
        treeDAO.insertTree(tree);
    }
}
