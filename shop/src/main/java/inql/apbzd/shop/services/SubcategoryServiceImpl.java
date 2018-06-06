package inql.apbzd.shop.services;

import inql.apbzd.shop.commands.SubcategoryCommand;
import inql.apbzd.shop.converters.SubcategoryCommandToSubcategory;
import inql.apbzd.shop.converters.SubcategoryToSubcategoryCommand;
import inql.apbzd.shop.domain.Subcategory;
import inql.apbzd.shop.repositories.SubcategoryRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class SubcategoryServiceImpl implements SubcategoryService {

    private final SubcategoryRepository subcategoryRepository;
    private final SubcategoryToSubcategoryCommand subcategoryToSubcategoryCommand;
    private final SubcategoryCommandToSubcategory subcategoryCommandToSubcategory;

    public SubcategoryServiceImpl(SubcategoryRepository subcategoryRepository, SubcategoryToSubcategoryCommand subcategoryToSubcategoryCommand, SubcategoryCommandToSubcategory subcategoryCommandToSubcategory) {
        this.subcategoryRepository = subcategoryRepository;
        this.subcategoryToSubcategoryCommand = subcategoryToSubcategoryCommand;
        this.subcategoryCommandToSubcategory = subcategoryCommandToSubcategory;
    }

    @Override
    public Set<Subcategory> getSubcategories() {
        Set<Subcategory> subcategorySet = new HashSet<>();
        subcategoryRepository.findAll().iterator().forEachRemaining(subcategorySet::add);
        return subcategorySet;
    }

    @Override
    public Subcategory findById(Long l) {
        Optional<Subcategory> subcategoryOptional = subcategoryRepository.findById(l);

        if(!subcategoryOptional.isPresent()){
            try {
                throw new NotFoundException("Nie znaleziono podaktegorii dla ID: "+l.toString());
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }

        return subcategoryOptional.get();
    }

    @Override
    @Transactional
    public SubcategoryCommand findCommandById(Long l) {
        return subcategoryToSubcategoryCommand.convert(findById(l));
    }

    @Override
    @Transactional
    public SubcategoryCommand saveSubcategoryCommand(SubcategoryCommand subcategoryCommand) {
        Subcategory detachedSubcategory = subcategoryCommandToSubcategory.convert(subcategoryCommand);

        Subcategory savedSubcategory = subcategoryRepository.save(detachedSubcategory);

        return subcategoryToSubcategoryCommand.convert(savedSubcategory);
    }

    @Override
    public void deleteById(Long l) {
        subcategoryRepository.deleteById(l);
    }
}
