package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t01.n03.controllers;

import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t01.n03.model.dto.FlorDTO;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t01.n03.model.services.FlorDTOService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Tag(name = "Flower", description = "Flower Client APIs")
@RestController
@RequestMapping("/api/v1")
public class FlorClientController
{
    private final FlorDTOService florDTOService;

    public FlorClientController(FlorDTOService florDTOService) {
        this.florDTOService = florDTOService;
    }

    @Operation(
            summary = "Get All Flowers",
            description = "Get all Flower objects. The response is a list of all objects with id, name, country and the type of Euro or Not Euro country",
            tags = {"get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = FlorDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping(path="/flor/clientFlorsAll", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Flux<FlorDTO> findAll() {
        return florDTOService.findAll();
    }

    @Operation(
            summary = "Retrieve a Flower by Id",
            description = "Get a Flower object by specifying its id. The response is Flower object with id, title, description and published status.",
            tags = {"get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = FlorDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "The Flower with given Id was not found.", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "409", description = "Flor ID cannot be null.", content = { @Content(schema = @Schema()) })})
    @GetMapping(value = "/flor/clientFlorsGetOne/{id}")
    public Mono<FlorDTO> findById(@PathVariable("id") Long id) {
        return florDTOService.findById(id);
    }

    @Operation(
            summary = "Create a Flower",
            description = "Create a Flower object. The response is a httpStatus showing the result of the operation.",
            tags = {"post"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = FlorDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "409", description = "Flor ID cannot be null.", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/flor/clientFlorsAdd")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<FlorDTO> create(@RequestBody FlorDTO e) {
        return florDTOService.create(e);
    }

    @Operation(
            summary = "Update a Flower by Id",
            description = "Update a Flower object by specifying its id. The response is a httpStatus showing the result of the operation.",
            tags = {"put"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = FlorDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "The Flower with given Id was not found.", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "409", description = "Flor ID cannot be null.", content = { @Content(schema = @Schema()) })})
    @PutMapping(value = "/flor/clientFlorsUpdate/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<FlorDTO> update(@RequestBody FlorDTO e, @PathVariable("id") Long id) {
        e.setPk_FlorID(id);
        return florDTOService.update(e);
    }

    @Operation(
            summary = "Delete a Flower by Id",
            description = "Delete a Flower object by specifying its id. The response is a httpStatus showing the result of the operation.",
            tags = {"delete"})
    @ApiResponses({ @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "The Flower with given Id was not found.", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping(value = "/clientFlorsDelete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> delete(@PathVariable("id") Long id) {
        return florDTOService.delete(id);
    }
}